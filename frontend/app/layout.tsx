import TopNav from "@/components/TopNav";
import type { Metadata } from "next";
import localFont from "next/font/local";
import "./globals.css";

const font = localFont({
  src: [
    {
      path: "../components/font/NotoSerifTC-ExtraLight.otf",
      weight: "200",
      style: "extralight",
    },
    {
      path: "../components/font/NotoSerifTC-Light.otf",
      weight: "300",
      style: "light",
    },
    {
      path: "../components/font/NotoSerifTC-Regular.otf",
      weight: "400",
      style: "normal",
    },
    {
      path: "../components/font/NotoSerifTC-Medium.otf",
      weight: "500",
      style: "medium",
    },
    {
      path: "../components/font/NotoSerifTC-SemiBold.otf",
      weight: "600",
      style: "semibold",
    },
    {
      path: "../components/font/NotoSerifTC-Bold.otf",
      weight: "700",
      style: "bold",
    },
    {
      path: "../components/font/NotoSerifTC-Black.otf",
      weight: "900",
      style: "black",
    },
  ],
  display: "block",
  preload: true,
  adjustFontFallback: false,
});

export const metadata: Metadata =
  process.env.NODE_ENV == "development"
    ? {
        title: "[dev] Hailey and Wei-Wei's Wedding",
        description: "development server for wedding website",
      }
    : {
        title: "Hailey and Wei-Wei's Wedding",
        description: "Everything related",
      };

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body
        className={`${font.className} antialiased border-r-gray-50 bg-red-900 text-red-100 h-screen`}
      >
        <TopNav />
        <main className="pt-52 pb-40 min-h-screen w-full">{children}</main>
      </body>
    </html>
  );
}
