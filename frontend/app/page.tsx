import TopNav from "@/components/TopNav";
import Link from "next/link";
import Hr from "./component/Hr";

export default function Home() {
  return (
    <div className="h-screen flex flex-col justify-center text-center align-middle">
      <TopNav />
      <Hr />
      <p className="font-extralight">
        <br />
        Please save your date for our wedding ceremony on 1 Dec 2024.
        <br />
        我們的婚禮將在 2024 年 12 月 1 日<br />
        於台北寒舍艾美酒店舉行
        <br />
        敬請保留時間共襄盛舉
      </p>
      <Link
        href={"/guest-details"}
        className="font-extralight text-red-900 text-sm"
      >
        D
      </Link>
      <Hr />
      <br />
      <iframe
        title="le meridien"
        src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d57843.81565744926!2d121.5193902!3d25.025979700000008!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3442abba50d70b7b%3A0x313d65ce289c8267!2z5Y-w5YyX5a-S6IiN6Im-576O6YWS5bqX!5e0!3m2!1szh-TW!2stw!4v1714287626753!5m2!1szh-TW!2stw"
        height="250"
        loading="lazy"
      />
    </div>
  );
}
