import Image, { StaticImageData } from "next/image";
import React from "react";

const Photo = ({
  imageSrc,
  alt,
}: {
  imageSrc: StaticImageData;
  alt?: string;
}) => {
  return (
    <div className="w-full md:w-1/3 aspect-[2/3] relative">
      <Image
        src={imageSrc}
        sizes="(max-width: 768px) 100vw, (max-width: 1200px) 50vw, 33vw"
        placeholder="blur"
        fill
        alt={alt ?? "Photo"}
      />
    </div>
  );
};

export default Photo;
