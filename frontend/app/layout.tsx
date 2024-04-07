import TopNav from "@/components/TopNav";
import type { Metadata } from "next";
import { Noto_Serif_TC } from "next/font/google";
import "./globals.css";

const font = Noto_Serif_TC({
  subsets: ["latin"],
  weight: ["400", "700"],
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
        className={`${font.className} antialiased border-r-gray-50 h-screen font-serif`}
      >
        <TopNav />
        <main className="pt-28 pb-40 min-h-screen w-full">{children}</main>
      </body>
    </html>
  );
}
