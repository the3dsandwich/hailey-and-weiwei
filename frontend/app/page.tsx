import SignupForm from "@/components/SignupForm";
import centerPortrait from "./images/sample-large-portrait.jpg";
import Link from "next/link";
import Photo from "./component/Photo";
import Hr from "./component/Hr";

export default function Home() {
  return (
    <section className="flex flex-col items-center">
      <div className="text-center w-10/12 flex flex-col items-center">
        <hr />
        <br />
        <Photo imageSrc={centerPortrait} />
        <Hr />
        <h1 className="text-cream-can-200 font-extrabold text-xl">
          婚禮邀請函
        </h1>
        <h1 className="text-red-100 font-extrabold text-xl">婚禮邀請函</h1>
        <h1 className="text-red-200 font-extrabold text-xl">婚禮邀請函</h1>
        <h1 className="text-red-300 font-extrabold text-xl">婚禮邀請函</h1>
        <h1 className="text-red-400 font-extrabold text-xl">婚禮邀請函</h1>
        <h1 className="text-red-500 font-extrabold text-xl">婚禮邀請函</h1>
        Welcome to our wedding
        <br />
        【婚禮】
        <br />
        人生是一場以愛之名的相聚
        <br />
        很開心這一天
        <br />
        你為我們而來
        <br />
        我 們 是 彼 此 親 自 挑 選 的 家 人
        <br />
        希望我們有幸
        <br />
        邀請收到喜帖的每一個你
        <br />
        見證我們此生最重要的決定
        <br />
        願我們繼續在
        <br />
        每一個溫暖的日子相約
        <br />
        <Hr />
        - 囍 -
        <br />
        <Hr />
        <Photo imageSrc={centerPortrait} />
        <Hr />
        人的一生有三萬多天
        <br />
        很開心這一天
        <br />
        你專為我們而來
        <br />
        Welcome to our wedding
        <br />
        <Hr />
        <Photo imageSrc={centerPortrait} />
        <Hr />
        致珍貴的你們
        <br />
        我們決定
        <br />
        在 2024 年 12 月 01 日這一天
        <br />
        將攜手走過無數個日夜
        <br />
        續約至無限個相互陪伴的時刻
        <br />
        <br />
        期待著我們在這一天重聚
        <br />
        不僅是一個婚禮 更像是一個 party
        <br />
        找個機會與久未見面的朋友們一起
        <br />
        談天說地 分享喜悅
        <br />
        更想與你分享我們的故事
        <br />
        <Hr />
        囍
        <br />
        <Hr />
        <Photo imageSrc={centerPortrait} />
        <br />
        <hr />
      </div>
      <div>
        <br />
        <h1>溫馨提醒</h1>
        <ul>
          <li>{"> "} tip 1</li>
          <li>{"> "}tip 2</li>
          <li>{"> "}tip 3</li>
          <li>
            {"> "}
            自行開車、騎車皆可免費消磁，請各位直接停進地下室，婚宴結束後拿給酒店工作人員消磁即可
          </li>
          <li>{"> "}嘔吐費 8,000 請各位理性飲酒，感恩，平安，阿門</li>
        </ul>
      </div>
      <br />
      <hr />
      <br />
      <div>
        <h1>婚宴日期 | Date</h1>
        2024 年 12 月 01 日 上午 11:30
        <h1>婚宴地址 | Address</h1>
        台北寒舍艾美酒店
        <br />
        台北市信義區松仁路38號
        <br />
        <iframe
          title="le meridien"
          src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d57843.81565744926!2d121.5193902!3d25.025979700000008!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3442abba50d70b7b%3A0x313d65ce289c8267!2z5Y-w5YyX5a-S6IiN6Im-576O6YWS5bqX!5e0!3m2!1szh-TW!2stw!4v1714287626753!5m2!1szh-TW!2stw"
          width="300"
          height="250"
          loading="lazy"
        />
      </div>
      <div className="text-center">
        <hr />
        <br />
        <Photo imageSrc={centerPortrait} />
        <Hr />
        <b>「感謝您/不遠千里/見證我們的愛情」</b>
        <br />
        <br />
        2024.12.01
        <br />
        我們，婚禮見
      </div>

      <div className="mt-8">
        <SignupForm />
      </div>
      <Link href={"/guest-details"} className="font-extralight text-sm pt-12">
        guest details
      </Link>
    </section>
  );
}
