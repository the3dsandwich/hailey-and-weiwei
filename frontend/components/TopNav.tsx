"use client";

import React from "react";
import Link from "next/link";

const TopNav = () => {
  const routeHome = "/";
  const routeAbout = "/about";
  const title = "Hailey and Wei-Wei's Wedding";

  return (
    <nav className="flex absolute h-20 w-full items-center border-b-2 border-gray-300 bg-gray-200 px-6">
      <Link className="mr-auto flex h-6" href={routeHome}>
        <h1 className="text-gray-900 font-bold text-xl">{title}</h1>
      </Link>
      <div className="text-gray-900 flex h-10 items-center">
        <Link
          className="inline-flex h-9 w-max items-center justify-center rounded-md px-4 font-bold text-sm transition-colors hover:bg-cream-can-100 hover:text-gray-900 focus:bg-cream-can-100 focus:text-gray-900 focus:outline-none disabled:pointer-events-none disabled:opacity-50"
          href={routeAbout}
        >
          About
        </Link>
      </div>
    </nav>
  );
};

export default TopNav;
