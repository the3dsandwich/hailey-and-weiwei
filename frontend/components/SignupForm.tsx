"use client";

import React from "react";

import { Label } from "@/components/ui/label";
import { Input } from "@/components/ui/input";
import { Checkbox } from "@/components/ui/checkbox";
import { Textarea } from "@/components/ui/textarea";
import { Button } from "@/components/ui/button";
import { FormEvent, useState } from "react";

const SignupForm = () => {
  const [isLoading, setIsLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);

  async function onSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    setIsLoading(true);
    setError(null); // Clear previous errors when a new request starts

    try {
      const formData = new FormData(event.currentTarget);
      const formJSON = {
        name: (
          event.currentTarget.elements.namedItem("name") as HTMLInputElement
        ).value,
        email: (
          event.currentTarget.elements.namedItem("email") as HTMLInputElement
        ).value,
        phone: (
          event.currentTarget.elements.namedItem("phone") as HTMLInputElement
        ).value,
        comments: (
          event.currentTarget.elements.namedItem("comments") as HTMLInputElement
        ).value,
        vegan: (
          event.currentTarget.elements.namedItem("vegan") as HTMLInputElement
        ).checked,
        vegetarian: (
          event.currentTarget.elements.namedItem(
            "vegetarian"
          ) as HTMLInputElement
        ).checked,
        glutenFree: (
          event.currentTarget.elements.namedItem(
            "gluten-free"
          ) as HTMLInputElement
        ).checked,
      };

      const endpoint =
        "https://haileyandweiweibackend.the3dsandwich.com/signup";
      const response = await fetch(endpoint, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formJSON),
      });

      if (!response.ok) {
        throw new Error("Failed to submit the data. Please try again.");
      }

      // Handle response if necessary
      const data = await response.json();
      // ...
      console.log({ endpoint, ...data });
      setError(data.message);
    } catch (error: any) {
      // Capture the error message to display to the user
      setError(error.message);
      console.error(error);
    } finally {
      setIsLoading(false);
    }
  }

  return (
    <>
      <form className="flex flex-col" onSubmit={onSubmit}>
        <div className="grid max-w-3xl gap-4 px-4 mx-auto lg:grid-cols-2 lg:px-6">
          <div className="space-y-4 lg:col-span-2">
            <div className="space-y-2">
              <h1 className="text-3xl font-bold tracking-tighter sm:text-4xl md:text-5xl/none">
                {"RSVP to Hailey and Wei-Wei's Wedding Event"}
              </h1>
              <p className="text-gray-500 dark:text-gray-400">
                Enter your information to RSVP
              </p>
            </div>
            <div className="space-y-2">
              <Label htmlFor="name">Name</Label>
              <Input id="name" placeholder="Enter your name" required />
            </div>
            <div className="grid grid-cols-1 gap-2 sm:grid-cols-2">
              <div className="space-y-2">
                <Label htmlFor="email">Email</Label>
                <Input
                  id="email"
                  placeholder="Enter your email"
                  required
                  type="email"
                />
              </div>
              <div className="space-y-2">
                <Label htmlFor="phone">Phone</Label>
                <Input
                  id="phone"
                  placeholder="Enter your phone"
                  required
                  type="tel"
                />
              </div>
            </div>
            <div className="space-y-2">
              <Label htmlFor="comments">
                Additional comments or special requests
              </Label>
              <Textarea
                id="comments"
                placeholder="Enter your comments"
                required
              />
            </div>
            <Button type="submit">RSVP</Button>
          </div>
        </div>
      </form>
      {error && <div className="text-red-500">{error}</div>}
    </>
  );
};

export default SignupForm;
