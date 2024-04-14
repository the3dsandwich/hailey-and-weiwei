import SignupForm from "@/components/SignupForm";
import Image from "next/image";
import centerPortrait from "./images/sample-large-portrait.jpg";
import Link from "next/link";

export default function Home() {
  return (
    <section className="flex flex-col items-center">
      <div className="w-full md:w-1/3 aspect-[2/3] relative">
        <Image
          src={centerPortrait}
          sizes="(max-width: 768px) 100vw, (max-width: 1200px) 50vw, 33vw"
          placeholder="blur"
          fill
          alt="Picture middle portrait"
        />
      </div>
      <div className="mt-8">
        <SignupForm />
      </div>
      <Link href={"/guest-details"}>guest details</Link>
    </section>
  );
}
