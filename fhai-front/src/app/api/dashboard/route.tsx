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

export async function POST(request: Request) {
  try {
    const body = await request.json();
    const resp = await fetch(
      `${process.env.NEXT_PUBLIC_API_URL}api/transacao`,
      {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(body),
      }
    );
    const data = await resp.json();
    return NextResponse.json(data);
  } catch (err) {
    console.error("Erro ao criar transação", err);
    return NextResponse.json(
      { error: "Falha ao criar transação" },
      { status: 500 }
    );
  }
}
