import SignupForm from "@/components/SignupForm";
import Image from "next/image";
import centerPortrait from "./images/sample-large-portrait.jpg";

export default function Home() {
  return (
    <main className="flex flex-col items-center min-h-screen pt-28 pb-40 w-full">
      <div className="w-full md:w-1/3 aspect-[2/3] relative">
        <Image
          src={centerPortrait}
          sizes="100vw"
          placeholder="blur"
          fill
          alt="Picture middle portrait"
        />
      </div>
      <div className="mt-8">
        <SignupForm />
      </div>
    </main>
  );
}
