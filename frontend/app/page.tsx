import SignupForm from "@/components/SignupForm";
import Image from "next/image";

export default function Home() {
  return (
    <main className="flex flex-col justify-center align-middle min-h-screen pt-20 pb-40">
      <Image
        src="/sample-large-portrait.png"
        width={700}
        height={475}
        sizes="100vw"
        style={{
          width: "100%",
          height: "auto",
        }}
        alt="Picture middle portrait"
      />
      <SignupForm />
    </main>
  );
}
