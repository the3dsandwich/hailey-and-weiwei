"use client";

import React, { useEffect } from "react";
import anime from "animejs";

const SplashScreen = ({ finishLoading }: { finishLoading?: () => void }) => {
  useEffect(() => {
    const loader = anime.timeline({
      complete: () => finishLoading && finishLoading(),
    });
    loader.add({
      targets: "#logo",
      delay: 100,
      scale: 1.3,
      rotate: -5,
      duration: 1000,
      easing: "easeOutElastic",
      endDelay: 500,
    });
    loader.add({
      targets: "#logo",
      delay: 0,
      opacity: 0,
      duration: 2000,
      easing: "easeInOutExpo",
    });
  });

  return (
    <div className="flex h-screen w-screen items-center justify-center">
      <p id="logo" className="text-center">
        HAILEY
        <br />
        AND
        <br />
        WEIWEI
      </p>
    </div>
  );
};

export default SplashScreen;
