import TopNav from "@/components/TopNav";

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <div className="flex flex-col mb-40">
      <TopNav />
      {children}
    </div>
  );
}
