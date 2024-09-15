"use client";

import Link from "next/link";
import SignupForm from "./SignupForm";

const TopNav = ({ showSignupForm }: { showSignupForm?: boolean }) => {
  const routeHome = "/";

  return (
    <nav className="flex flex-col w-full mt-10 mb-10 items-center justify-center">
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
            </a>{" "}
            <a
              href="https://maps.app.goo.gl/cHEWEB56d5x1qjXv6"
              target="_blank"
              className="underline"
            >
              (directions)
            </a>
          </i>
        </small>
        <br />
        {showSignupForm && <SignupForm small />}
      </p>
    </nav>
  );
};

export default TopNav;
