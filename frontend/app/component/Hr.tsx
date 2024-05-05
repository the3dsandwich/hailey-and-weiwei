import React from "react";

const Hr = () => {
  return (
    <div className="w-10/12">
      <div className="inline-flex items-center justify-center w-full">
        <hr className="w-64 h-px my-8 bg-red-200 border-0 dark:bg-red-700" />
        <span className="absolute px-3 text-red-900 -translate-x-1/2 bg-red-900 left-1/2 dark:text-red-100 dark:bg-red-900">
          💎
        </span>
      </div>
    </div>
  );
};

export default Hr;
