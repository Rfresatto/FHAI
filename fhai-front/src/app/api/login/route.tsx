// src/app/api/dashboard/login/route.ts
import { NextResponse } from "next/server";

export async function POST(request: Request) {
  const { email, password } = await request.json();

  if (!email || !password) {
    return NextResponse.json(
      { message: "Email e senha são obrigatórios" },
      { status: 400 }
    );
  }

  if (email === "renan@gmail.com" && password === "123") {
    return NextResponse.json({ token: "fake-admin-token" });
  }

  return NextResponse.json(
    { message: "Credenciais inválidas" },
    { status: 401 }
  );
}
