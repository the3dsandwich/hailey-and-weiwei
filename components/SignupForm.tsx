"use client";

import React from "react";

import { Label } from "@/components/ui/label";
import { Input } from "@/components/ui/input";
import { Select } from "@/components/ui/select";
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
        name: event.target.name.value,
        email: event.target.email.value,
        phone: event.target.phone.value,
        comments: event.target.comments.value,
        vegan: event.target.vegan.checked,
        vegetarian: event.target.vegetarian.checked,
        glutenFree: event.target.glutenFree.checked,
      };

      const response = await fetch("/api/submit", {
        method: "POST",
        body: JSON.stringify(formJSON),
      });

      if (!response.ok) {
        throw new Error("Failed to submit the data. Please try again.");
      }

      // Handle response if necessary
      const data = await response.json();
      // ...
      console.log(data);
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
        <div className="grid max-w-2xl gap-4 px-4 mx-auto lg:grid-cols-2 lg:px-6">
          <div className="space-y-4 lg:col-span-2">
            <div className="space-y-2">
              <h1 className="text-3xl font-bold tracking-tighter sm:text-4xl md:text-5xl/none">
                RSVP to our Wedding Event
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
              <Label htmlFor="guests">Number of guests</Label>
              <Select id="guests">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
              </Select>
            </div>
            <fieldset className="space-y-2">
              <legend className="text-lg font-medium">
                Meal preferences (check all that apply)
              </legend>
              <div className="space-y-2">
                <Checkbox id="vegan" />
                vegan
                <Checkbox id="vegetarian" />
                vegetarian
                <Checkbox id="glutenFree" />
                gluten-free
              </div>
            </fieldset>
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
