import { NextResponse } from "next/server";

export async function GET() {
  try {
    const response = fetch(`${process.env.API_URL}/usuario`);
    const data = (await response).json();
    return NextResponse.json(data);
  } catch (err) {
    console.error("Error fetching user: ", err);
    return NextResponse.json(
      { error: "Falha ao recuperar usuarios" },
      { status: 500 }
    );
  }
}
