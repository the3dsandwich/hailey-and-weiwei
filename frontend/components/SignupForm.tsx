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
import { Textarea } from "./ui/textarea";

const formSchema = z.object({
  name: z.string(),
  nickname: z.string(),
  email: z.string().email(),
  comments: z.string(),
  friendOf: z.string(),
  transportation: z.enum(["drive", "motorcycle", "ride", "none"]),
  isPhysicalInvitation: z.boolean(),
  physicalAddress: z.string(),
  vegetarian: z.boolean(),
  isBringCompanion: z.boolean(),
  companionName: z.string(),
  companionVegetarian: z.boolean(),
});

const HOST =
  process.env.NODE_ENV == "development"
    ? "http://localhost:8081"
    : "https://haileyandweiweibackend.the3dsandwich.com";

const SignupForm = ({
  small,
  disabled,
}: {
  small?: boolean;
  disabled?: boolean;
}) => {
  const [isLoading, setIsLoading] = useState<boolean>(false);
  const [open, setOpen] = useState(false);

  const form = useForm<z.infer<typeof formSchema>>({
    resolver: zodResolver(formSchema),
    defaultValues: {
      name: "",
      nickname: "",
      email: "",
      comments: "",
      friendOf: "",
      transportation: "none",
      isPhysicalInvitation: false,
      physicalAddress: "",
      vegetarian: false,
      isBringCompanion: false,
      companionName: "",
      companionVegetarian: false,
    },
  });

  const onSubmit = async (values: z.infer<typeof formSchema>) => {
    setIsLoading(true);

    try {
      console.log(values);

      const endpoint = HOST + "/signup";
      const response = await fetch(endpoint, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Allow-Headers": HOST,
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
      form.reset();
      setOpen(false);
    }
  };

  const displayText = () => {
    if (disabled) return "2024.12.01 | 期待與您相見";
    return small ? "Signup!" : "我們的婚禮表單 | Signup";
  };

  return (
    <>
      <Toaster />
      <Dialog open={open} onOpenChange={setOpen}>
        <DialogTrigger asChild>
          <Button
            variant={small ? "link" : "outline"}
            size={small ? "sm" : "default"}
            style={small ? { textDecoration: "underline" } : {}}
            disabled={disabled}
          >
            {displayText()}
          </Button>
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
                          <SelectValue placeholder="誰的親友 | I am here for..." />
                        </SelectTrigger>

                        <SelectPortal>
                          <SelectContent>
                            <SelectScrollUpButton />
                            <SelectViewport>
                              <SelectGroup>
                                <SelectLabel>男方親友</SelectLabel>
                                <SelectItem value="Wei-Wei">
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
                    <FormLabel>
                      名字<span className="text-red-700"> *</span>
                    </FormLabel>
                    <FormControl>
                      <Input
                        placeholder="你的名字 | My name is..."
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
                    <FormLabel>
                      Email<span className="text-red-700"> *</span>
                    </FormLabel>
                    <FormControl>
                      <Input
                        placeholder="你的 email | My email is..."
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
              {/* <FormField
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
              /> */}
              {form.watch("isPhysicalInvitation") && (
                <FormField
                  control={form.control}
                  name="physicalAddress"
                  render={({ field }) => (
                    <FormItem>
                      <FormLabel>
                        地址<span className="text-red-700"> *</span>
                      </FormLabel>
                      <FormControl>
                        <Input
                          placeholder="你的地址 | You can mail me at..."
                          required
                          {...field}
                        />
                      </FormControl>
                    </FormItem>
                  )}
                />
              )}
              <FormField
                control={form.control}
                name="transportation"
                render={({ field: { onChange, ...fieldProps } }) => (
                  <FormItem>
                    <FormLabel>交通方式</FormLabel>
                    <FormControl>
                      <Select {...fieldProps} onValueChange={onChange}>
                        <SelectTrigger aria-label="transportation">
                          <SelectValue placeholder="來的方式 | I will arrive by..." />
                        </SelectTrigger>

                        <SelectPortal>
                          <SelectContent>
                            <SelectScrollUpButton />
                            <SelectViewport>
                              <SelectGroup>
                                <SelectLabel>需要停車 (免費消磁)</SelectLabel>
                                <SelectItem value="drive">🚗 開車車</SelectItem>
                                <SelectItem value="motorcycle">
                                  🏍️ 騎車車
                                </SelectItem>
                              </SelectGroup>

                              <SelectSeparator />

                              <SelectGroup>
                                <SelectLabel>不需消磁</SelectLabel>
                                <SelectItem value="ride">
                                  🚕 別人開車/大眾運輸/腳踏車/走路
                                </SelectItem>
                              </SelectGroup>

                              <SelectSeparator />

                              <SelectGroup>
                                <SelectLabel>？？</SelectLabel>
                                <SelectItem value="none">🤷 待確認</SelectItem>
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
                name="isBringCompanion"
                render={({ field: { onChange, ...fieldProps } }) => (
                  <FormItem>
                    <FormLabel>是否攜伴</FormLabel>
                    <FormControl>
                      <Checkbox onCheckedChange={onChange} />
                    </FormControl>
                  </FormItem>
                )}
              />
              {form.watch("isBringCompanion") && (
                <>
                  <FormField
                    control={form.control}
                    name="companionName"
                    render={({ field }) => (
                      <FormItem>
                        <FormLabel>
                          攜伴名字<span className="text-red-700"> *</span>
                        </FormLabel>
                        <FormControl>
                          <Input
                            placeholder="攜伴名稱 | Companion's name"
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
                    name="companionVegetarian"
                    render={({ field: { onChange, ...fieldProps } }) => (
                      <FormItem>
                        <FormLabel>攜伴素食</FormLabel>
                        <FormControl>
                          <Checkbox onCheckedChange={onChange} />
                        </FormControl>
                      </FormItem>
                    )}
                  />
                </>
              )}
              <FormField
                control={form.control}
                name="comments"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>備註</FormLabel>
                    <FormControl>
                      <Textarea
                        rows={5}
                        placeholder="留言給我們！ | I want to tell Hailey and Wei-Wei that..."
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
