import type { Config } from "tailwindcss";

const config = {
  darkMode: ["class"],
  content: [
    "./pages/**/*.{ts,tsx}",
    "./components/**/*.{ts,tsx}",
    "./app/**/*.{ts,tsx}",
    "./src/**/*.{ts,tsx}",
  ],
  prefix: "",
  theme: {
    container: {
      center: true,
      padding: "2rem",
      screens: {
        "2xl": "1400px",
      },
    },
    extend: {
      colors: {
        border: "hsl(var(--border))",
        input: "hsl(var(--input))",
        ring: "hsl(var(--ring))",
        background: "hsl(var(--background))",
        foreground: "hsl(var(--foreground))",
        primary: {
          DEFAULT: "hsl(var(--primary))",
          foreground: "hsl(var(--primary-foreground))",
        },
        secondary: {
          DEFAULT: "hsl(var(--secondary))",
          foreground: "hsl(var(--secondary-foreground))",
        },
        destructive: {
          DEFAULT: "hsl(var(--destructive))",
          foreground: "hsl(var(--destructive-foreground))",
        },
        muted: {
          DEFAULT: "hsl(var(--muted))",
          foreground: "hsl(var(--muted-foreground))",
        },
        accent: {
          DEFAULT: "hsl(var(--accent))",
          foreground: "hsl(var(--accent-foreground))",
        },
        popover: {
          DEFAULT: "hsl(var(--popover))",
          foreground: "hsl(var(--popover-foreground))",
        },
        card: {
          DEFAULT: "hsl(var(--card))",
          foreground: "hsl(var(--card-foreground))",
        },
        "congress-blue": {
          "50": "hsl(210, 100%, 97%)",
          "100": "hsl(210, 100%, 94%)",
          "200": "hsl(206, 100%, 86%)",
          "300": "hsl(205, 100%, 73%)",
          "400": "hsl(204, 100%, 59%)",
          "500": "hsl(205, 97%, 48%)",
          "600": "hsl(207, 100%, 41%)",
          "700": "hsl(208, 100%, 33%)",
          "800": "hsl(207, 99%, 27%)",
          "900": "hsl(208, 89%, 24%)",
          "950": "hsl(210, 88%, 16%)",
          DEFAULT: "hsl(207, 99%, 27%)", // 800
        },
        "cream-can": {
          "50": "hsl(43, 90%, 96%)",
          "100": "hsl(46, 82%, 89%)",
          "200": "hsl(45, 83%, 77%)",
          "300": "hsl(43, 84%, 61%)",
          "400": "hsl(40, 84%, 56%)",
          "500": "hsl(35, 80%, 50%)",
          "600": "hsl(29, 82%, 44%)",
          "700": "hsl(23, 79%, 37%)",
          "800": "hsl(19, 71%, 31%)",
          "900": "hsl(18, 67%, 26%)",
          "950": "hsl(18, 81%, 14%)",
          DEFAULT: "hsl(43, 84%, 61%)", // 300
        },
        "witch-haze": {
          "50": "hsl(57, 92%, 95%)",
          "100": "hsl(57, 100%, 88%)",
          "200": "hsl(56, 100%, 79%)",
          "300": "hsl(53, 100%, 64%)",
          "400": "hsl(51, 97%, 53%)",
          "500": "hsl(48, 95%, 47%)",
          "600": "hsl(43, 98%, 40%)",
          "700": "hsl(38, 93%, 33%)",
          "800": "hsl(35, 82%, 29%)",
          "900": "hsl(31, 74%, 26%)",
          "950": "hsl(29, 86%, 14%)",
          DEFAULT: "hsl(56, 100%, 79%)", // 200
        },
        "hairy-heath": {
          "50": "hsl(37, 89%, 96%)",
          "100": "hsl(38, 90%, 92%)",
          "200": "hsl(35, 86%, 83%)",
          "300": "hsl(33, 86%, 72%)",
          "400": "hsl(29, 85%, 61%)",
          "500": "hsl(27, 84%, 53%)",
          "600": "hsl(23, 80%, 48%)",
          "700": "hsl(20, 79%, 40%)",
          "800": "hsl(18, 70%, 34%)",
          "900": "hsl(18, 66%, 23%)",
          "950": "hsl(16, 73%, 15%)",
          DEFAULT: "hsl(18, 66%, 23%)", // 900
        },
        "apricot-peach": {
          "50": "hsl(27, 100%, 96%)",
          "100": "hsl(27, 95%, 92%)",
          "200": "hsl(25, 95%, 84%)",
          "300": "hsl(23, 94%, 72%)",
          "400": "hsl(20, 94%, 61%)",
          "500": "hsl(17, 92%, 53%)",
          "600": "hsl(13, 88%, 48%)",
          "700": "hsl(10, 86%, 40%)",
          "800": "hsl(8, 77%, 34%)",
          "900": "hsl(8, 73%, 28%)",
          "950": "hsl(5, 78%, 15%)",
          DEFAULT: "hsl(25, 95%, 84%)", // 200
        },
      },
      borderRadius: {
        lg: "var(--radius)",
        md: "calc(var(--radius) - 2px)",
        sm: "calc(var(--radius) - 4px)",
      },
      keyframes: {
        "accordion-down": {
          from: { height: "0" },
          to: { height: "var(--radix-accordion-content-height)" },
        },
        "accordion-up": {
          from: { height: "var(--radix-accordion-content-height)" },
          to: { height: "0" },
        },
      },
      animation: {
        "accordion-down": "accordion-down 0.2s ease-out",
        "accordion-up": "accordion-up 0.2s ease-out",
      },
    },
  },
  plugins: [require("tailwindcss-animate")],
} satisfies Config;

export default config;
