"use client";

import { Button } from "@/components/ui/button";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog";
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import {
  Select,
  SelectContent,
  SelectGroup,
  SelectItem,
  SelectLabel,
  SelectScrollDownButton,
  SelectScrollUpButton,
  SelectSeparator,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import { Toaster } from "@/components/ui/sonner";
import { zodResolver } from "@hookform/resolvers/zod";
import { SelectPortal, SelectViewport } from "@radix-ui/react-select";
import { useState } from "react";
import { useForm } from "react-hook-form";
import { toast } from "sonner";
import { z } from "zod";
import { Checkbox } from "./ui/checkbox";

const formSchema = z.object({
  name: z.string(),
  nickname: z.string(),
  email: z.string().email(),
  phone: z.string(),
  comments: z.string(),
  friendOf: z.string(),
  transportation: z.enum([
    "drive",
    "motorcycle",
    "bike",
    "public",
    "ride",
    "uber",
    "none",
  ]),
  isPhysicalInvitation: z.boolean(),
  physicalAddress: z.string(),
  vegetarian: z.boolean(),
});

const SignupForm = () => {
  const [isLoading, setIsLoading] = useState<boolean>(false);

  const form = useForm<z.infer<typeof formSchema>>({
    resolver: zodResolver(formSchema),
    defaultValues: {
      name: "",
      nickname: "",
      email: "",
      phone: "",
      comments: "",
      friendOf: "",
      transportation: "none",
      isPhysicalInvitation: false,
      physicalAddress: "",
      vegetarian: false,
    },
  });

  const onSubmit = async (values: z.infer<typeof formSchema>) => {
    setIsLoading(true);

    try {
      console.log(values);

      const endpoint =
        process.env.NODE_ENV == "development"
          ? "http://localhost:8081/signup"
          : "https://haileyandweiweibackend.the3dsandwich.com/signup";
      const response = await fetch(endpoint, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Allow-Headers":
            process.env.NODE_ENV == "development"
              ? "http://localhost:8081"
              : "https://haileyandweiweibackend.the3dsandwich.com",
        },
        body: JSON.stringify(values),
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
  };

  return (
    <>
      <Toaster />
      <Dialog>
        <DialogTrigger asChild>
          <Button variant="outline">我們的婚禮表單 | Signup</Button>
        </DialogTrigger>
        <DialogContent className="sm:max-w-[728px]">
          <DialogHeader>
            <DialogTitle>RSVP to Hailey and Wei-Wei&apos;s Wedding</DialogTitle>
            <DialogDescription>感謝您的參與！</DialogDescription>
          </DialogHeader>

          <Form {...form}>
            <form
              onSubmit={form.handleSubmit(onSubmit)}
              className="grid gap-4 py-4"
            >
              <FormField
                control={form.control}
                name="friendOf"
                render={({ field: { onChange, ...fieldProps } }) => (
                  <FormItem>
                    <FormLabel>親友</FormLabel>
                    <FormControl>
                      <Select {...fieldProps} onValueChange={onChange}>
                        <SelectTrigger aria-label="friend-of">
                          <SelectValue placeholder="friend of..." />
                        </SelectTrigger>

                        <SelectPortal>
                          <SelectContent>
                            <SelectScrollUpButton />
                            <SelectViewport>
                              <SelectGroup>
                                <SelectLabel>男方親友</SelectLabel>
                                <SelectItem value="weiwei">
                                  🤵🏻 Wei-Wei Yeh
                                </SelectItem>
                              </SelectGroup>

                              <SelectSeparator />

                              <SelectGroup>
                                <SelectLabel>女方親友</SelectLabel>
                                <SelectItem value="Hailey">
                                  👰🏻‍♀️ Hailey Hsu
                                </SelectItem>
                              </SelectGroup>

                              <SelectSeparator />

                              <SelectGroup>
                                <SelectLabel>雙方親友</SelectLabel>
                                <SelectItem value="BOL">
                                  ⛪️ 台北靈糧堂
                                </SelectItem>
                              </SelectGroup>
                            </SelectViewport>
                            <SelectScrollDownButton />
                          </SelectContent>
                        </SelectPortal>
                      </Select>
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
              <FormField
                control={form.control}
                name="name"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>名字</FormLabel>
                    <FormControl>
                      <Input
                        placeholder="你的名字 | Enter your name"
                        required
                        {...field}
                      />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
              <FormField
                control={form.control}
                name="email"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Email</FormLabel>
                    <FormControl>
                      <Input
                        placeholder="你的 email | Enter your email"
                        required
                        type="email"
                        {...field}
                      />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
              <FormField
                control={form.control}
                name="phone"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>電話</FormLabel>
                    <FormControl>
                      <Input
                        placeholder="你的手機 | Enter your phone"
                        required
                        type="tel"
                        {...field}
                      />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
              <FormField
                control={form.control}
                name="vegetarian"
                render={({ field: { onChange, ...fieldProps } }) => (
                  <FormItem>
                    <FormLabel>素食</FormLabel>
                    <FormControl>
                      <Checkbox onCheckedChange={onChange} />
                    </FormControl>
                  </FormItem>
                )}
              />
              <FormField
                control={form.control}
                name="isPhysicalInvitation"
                render={({ field: { onChange, ...fieldProps } }) => (
                  <FormItem>
                    <FormLabel>實體喜帖</FormLabel>
                    <FormControl>
                      <Checkbox onCheckedChange={onChange} />
                    </FormControl>
                  </FormItem>
                )}
              />
              <FormField
                control={form.control}
                name="physicalAddress"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>地址</FormLabel>
                    <FormControl>
                      <Input
                        placeholder="你的地址 | Enter your physical address"
                        required
                        {...field}
                      />
                    </FormControl>
                  </FormItem>
                )}
              />
              <FormField
                control={form.control}
                name="comments"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>備註</FormLabel>
                    <FormControl>
                      <Input
                        placeholder="留言給我們！ | Enter your comments"
                        {...field}
                      />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
              <DialogFooter>
                <Button type="submit" disabled={isLoading}>
                  Sign me up!
                </Button>
              </DialogFooter>
            </form>
          </Form>
        </DialogContent>
      </Dialog>
    </>
  );
};

export default SignupForm;
