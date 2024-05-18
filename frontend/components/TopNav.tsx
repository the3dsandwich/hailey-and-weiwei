"use client";

import Link from "next/link";
import SignupForm from "./SignupForm";

const TopNav = () => {
  const routeHome = "/";
  const title = "Hailey & Wei-Wei";

  return (
    <nav className="flex flex-col absolute w-full h-56 items-center justify-center">
      <Link href={routeHome}>
        <h1 className="text-red-100 font-extralight text-3xl">
          Hailey & Wei-Wei&apos;s
        </h1>
      </Link>
      <p className="text-red-100 text-center mt-2">
        Wedding invitation
        <br />
        <small>
          <i>
            1 Dec 2024 |{" "}
            <a href="https://maps.app.goo.gl/cHEWEB56d5x1qjXv6" target="_blank">
              Le Méridien Taipei
            </a>
          </i>
        </small>
        <br />
        <SignupForm small />
      </p>
    </nav>
  );
};

export default TopNav;
