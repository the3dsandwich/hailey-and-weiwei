import { GuestDetailTable } from "@/components/GuestDetailTable";
import React from "react";
import Hr from "../component/Hr";
import { random } from "animejs";

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
          friendOf: string;
          tags: [string];
        }) => ({
          name: d.name,
          email: d.email,
          comments: d.comments,
          friendOf: d.friendOf,
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
      friendOf?: string;
      tags?: [string];
    }
  ]
) => {
  const countTags: Map<string, number> = new Map();
  data.forEach(({ tags }) => {
    const labelTag = tags?.[0];
    if (labelTag) {
      countTags.set(labelTag, (countTags.get(labelTag) ?? 0) + 1);
    }
  });

  return loading ? (
    <h1>loading...</h1>
  ) : (
    <>
      <Hr />
      <h1 className="text-2xl mb-12">已填賓客名單 (total: {data?.length})</h1>
      <p>
        {countTags
          .entries()
          .toArray()
          .map((value, index) => (
            <span key={value[0] + index} className="text-xs">
              {value[0]}: {value[1]}
              {" 人 "}
            </span>
          ))}
      </p>
      <GuestDetailTable dataList={data} />
    </>
  );
};

export default GuestDetails;
