import type { Metadata } from "next";
import { Noto_Serif_TC } from "next/font/google";
import "./globals.css";
import TopNav from "@/components/TopNav";

const font = Noto_Serif_TC({
  subsets: ["latin"],
  weight: ["400", "700"],
});

export const metadata: Metadata = {
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
        {children}
      </body>
    </html>
  );
}
