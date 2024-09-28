import { GuestDetailTable } from "@/components/GuestDetailTable";
import React from "react";
import Hr from "../component/Hr";
import Link from "next/link";

const GuestDetails = async () => {
  const fetchGuestList = async () => {
    const endpoint =
      process.env.NODE_ENV == "development"
        ? "http://localhost:8081/listGuests"
        : "https://haileyandweiweibackend.the3dsandwich.com/listGuests";
    const response = await fetch(endpoint, {
      method: "GET",
      cache: "no-store",
    });
    if (!response.ok) {
      throw new Error(`Failed to get data from ${endpoint}. Please try again.`);
    }
    const data = await response.json();
    console.log(data);

    return {
      loading: false,
      data: data?.guestList.map(
        (d: {
          name: string;
          email: string;
          comments: string;
          tags: [string];
        }) => ({
          name: d.name,
          email: d.email,
          comments: d.comments,
          tags: d.tags,
        })
      ),
    };
  };

  try {
    const { loading, data } = await fetchGuestList();
    return GuestDetail(loading, data);
  } catch (any) {
    return GuestDetail(true, [{}]);
  }
};

const GuestDetail = (
  loading: boolean,
  data: [
    {
      name?: string;
      email?: string;
      comments?: string;
      tags?: [string];
    }
  ]
) => {
  return loading ? (
    <h1>loading...</h1>
  ) : (
    <>
      <Link type="button" href={"/"} className="font-extralight text-sm">
        (signup)
      </Link>
      <Hr />
      <h1 className="text-2xl mb-12">GUEST LIST</h1>
      <GuestDetailTable dataList={data} />
    </>
  );
};

export default GuestDetails;
