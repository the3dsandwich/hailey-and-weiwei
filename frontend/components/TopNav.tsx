"use client";

import React from "react";
import Link from "next/link";

const TopNav = () => {
  const routeHome = "/";
  const title = "Hailey & Wei-Wei";

  return (
    <nav className="flex flex-col absolute w-full h-28 items-center justify-center bg-cream-can-100 px-6">
      <Link href={routeHome}>
        <h1 className="text-gray-800 font-bold text-3xl">{title}</h1>
      </Link>
    </nav>
  );
};

export default TopNav;
