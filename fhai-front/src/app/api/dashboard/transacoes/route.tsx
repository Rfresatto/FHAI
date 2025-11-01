import { NextResponse } from "next/server";

export async function GET() {
  try {
    const resp = await fetch(`${process.env.NEXT_PUBLIC_API_URL}api/transacao`);
    const data = await resp.json();
    return NextResponse.json(data);
  } catch (err) {
    console.error("Error fetching transacoes: ", err);
    return NextResponse.json(
      { error: "Falha ao recuperar transacoes" },
      { status: 500 }
    );
  }
}
