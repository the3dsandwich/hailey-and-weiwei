import TopNav from "@/components/TopNav";
import type { Metadata } from "next";
import localFont from "next/font/local";
import "./globals.css";

const font = localFont({
  src: [
    {
      path: "../components/font/NotoSerifTC-ExtraLight.otf",
      weight: "200",
      style: "normal",
    },
    {
      path: "../components/font/NotoSerifTC-Light.otf",
      weight: "300",
      style: "normal",
    },
    {
      path: "../components/font/NotoSerifTC-Regular.otf",
      weight: "400",
      style: "normal",
    },
    {
      path: "../components/font/NotoSerifTC-Medium.otf",
      weight: "500",
      style: "normal",
    },
    {
      path: "../components/font/NotoSerifTC-SemiBold.otf",
      weight: "600",
      style: "normal",
    },
    {
      path: "../components/font/NotoSerifTC-Bold.otf",
      weight: "700",
      style: "normal",
    },
    {
      path: "../components/font/NotoSerifTC-Black.otf",
      weight: "900",
      style: "normal",
    },
  ],
  display: "block",
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
      <head>
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link
          rel="preconnect"
          href="https://fonts.gstatic.com"
          crossOrigin="anonymous"
        />
      </head>
      <body
        className={`${font.className} antialiased border-r-gray-50 bg-red-900 text-red-100 h-screen font-serif`}
      >
        <TopNav />
        <main className="pt-52 pb-40 min-h-screen w-full">{children}</main>
      </body>
    </html>
  );
}
