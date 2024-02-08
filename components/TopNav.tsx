"use client";

import React from "react";
import Link from "next/link";

const TopNav = () => {
  const routeHome = "/";
  const routeAbout = "/about";
  const title = "Hailey and Wei-Wei's Wedding";
  const titleButtonStyles =
    "inline-flex h-9 w-max items-center justify-center rounded-md px-4 text-sm font-medium transition-colors hover:bg-yellow-100 hover:text-gray-900 focus:bg-yellow-100 focus:text-gray-900 focus:outline-none disabled:pointer-events-none disabled:opacity-50";

  return (
    <nav className="flex h-14 w-full items-center border-b border-gray-200 bg-pink-100 px-6">
      <Link className="mr-auto flex h-6" href={routeHome}>
        <h1 className="text-gray-900 font-bold text-xl font-serif">{title}</h1>
      </Link>
      <div className="flex h-10 items-center">
        <Link className={`${titleButtonStyles} mr-1`} href={routeHome}>
          Home
        </Link>
        <Link className={`${titleButtonStyles}`} href={routeAbout}>
          About
        </Link>
      </div>
    </nav>
  );
};

export default TopNav;
