import TopNav from "@/components/TopNav";
import Link from "next/link";
import Hr from "./component/Hr";
import { Button } from "@/components/ui/button";
import { WANG0795_謝卡2 } from "./images/images";

export default function Home() {
  return (
    <div className="h-screen flex flex-col justify-center text-center items-center">
      <TopNav />
      <Hr />
      <p className="font-extralight">
        <br />
        Please save your date for our wedding ceremony on 1 Dec 2024.
        <br />
        我們的婚禮將在 2024 年 12 月 1 日中午
        <br />
        於台北寒舍艾美酒店舉行
        <br />
        敬請保留時間共襄盛舉
      </p>
      <Link href={"/signup"} className="mt-4">
        <Button variant="outline" size="default">
          前往報名表單！
        </Button>
      </Link>
      <br />
      <Hr />
      <br />
      <WANG0795_謝卡2 />
    </div>
  );
}
