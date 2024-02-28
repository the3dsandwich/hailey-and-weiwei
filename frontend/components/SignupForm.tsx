"use client";

import React from "react";

import { Label } from "@/components/ui/label";
import { Input } from "@/components/ui/input";
import { Textarea } from "@/components/ui/textarea";
import { Button } from "@/components/ui/button";
import { FormEvent, useState } from "react";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "./ui/dialog";
import { toast } from "sonner";
import { Toaster } from "./ui/sonner";

const SignupForm = () => {
  const [isLoading, setIsLoading] = useState<boolean>(false);

  async function onSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    setIsLoading(true);

    try {
      const formData = new FormData(event.currentTarget);
      const formJSON = {
        name: formData.get("name")?.toString(),
        email: formData.get("email")?.toString(),
        phone: formData.get("phone")?.toString(),
        comments: formData.get("comments")?.toString(),
      };
      console.log(formJSON);

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
      console.trace({ endpoint, ...data });
      toast("RSVP success!", {
        description: "Check your email, something should be there in a bit",
      });
    } catch (error: any) {
      // Capture the error message to display to the user
      console.error(error.message);
      toast("RSVP failed", {
        description: `${error.message}`,
      });
    } finally {
      setIsLoading(false);
    }
  }

  return (
    <>
      <Toaster />
      <Dialog>
        <DialogTrigger asChild>
          <Button variant="outline">Unlock guest details</Button>
        </DialogTrigger>
        <DialogContent className="sm:max-w-[425px]">
          <DialogHeader>
            <DialogTitle>RSVP to Hailey and Wei-Wei&apos;s Wedding</DialogTitle>
            <DialogDescription>Thanks for joining us!</DialogDescription>
          </DialogHeader>

          <form onSubmit={onSubmit}>
            <div className="grid gap-4 py-4">
              <div className="grid grid-cols-6 items-center gap-4">
                <Label htmlFor="name">Name</Label>
                <Input
                  id="name"
                  placeholder="Enter your name"
                  className="col-span-5"
                  required
                />
              </div>
              <div className="grid grid-cols-6 items-center gap-4">
                <Label htmlFor="email">Email</Label>
                <Input
                  id="email"
                  placeholder="Enter your email"
                  className="col-span-5"
                  required
                  type="email"
                />
              </div>
              <div className="grid grid-cols-6 items-center gap-4">
                <Label htmlFor="phone">Phone</Label>
                <Input
                  id="phone"
                  placeholder="Enter your phone"
                  className="col-span-5"
                  required
                  type="tel"
                />
              </div>
              <div className="grid gap-4">
                <Label htmlFor="comments">
                  Additional comments or special requests
                </Label>
                <Textarea id="comments" placeholder="Enter your comments" />
              </div>
            </div>
            <DialogFooter>
              <Button type="submit" disabled={isLoading}>
                Sign me up!
              </Button>
            </DialogFooter>
          </form>
        </DialogContent>
      </Dialog>
    </>
  );
};

export default SignupForm;
