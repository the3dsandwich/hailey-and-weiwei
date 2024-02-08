export const dynamic = "force-dynamic"; // defaults to auto

export async function GET(request: Request) {
  return Response.json({ message: "Hello, world!" });
}

export async function POST(request: Request) {
  const formData = await request.json();
  const { name, email, phone, comments } = formData;
  console.log("received form data:", formData);
  return Response.json({
    message: `hello ${name} with email ${email}, I WILL CALL YOU AT ${phone} and I will read your comments which is ${comments}`,
  });
}
