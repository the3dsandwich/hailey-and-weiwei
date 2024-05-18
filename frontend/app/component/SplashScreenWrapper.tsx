"use client";

import React, { useEffect, useState } from "react";
import SplashScreen from "./SplashScreen";

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  const [delayFinished, setDelayFinished] = useState(false);

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
