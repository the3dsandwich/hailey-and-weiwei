"use client";

import React, { useState } from "react";
import SplashScreen from "./SplashScreen";

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  const [delayFinished, setDelayFinished] = useState(
    process.env.NODE_ENV == "development"
    // false
  );

  const handleFinishSplashScreen = () => {
    setDelayFinished(true);
  };

  return (
    <>
      {delayFinished ? (
        children
      ) : (
        <SplashScreen finishLoading={handleFinishSplashScreen} />
      )}
    </>
  );
}
