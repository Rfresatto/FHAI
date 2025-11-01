"use client";
import { CardeDeTransacao } from "@/components/CardDeTransacao";
import { useRouter } from "next/navigation";
import { useState } from "react";

export default function LoginPage() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState<string | null>(null);

  const router = useRouter();

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();
    setError(null);

    if (!email || !password) {
      setError("Por favor, preencha todos os campos");
    }

    try {
      const res = await fetch("/api/dashboard/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password }),
      });

      if (!res.ok) {
        const body = await res.json();
        throw new Error(body.message || "Falha na autenticação");
      }

      const data = await res.json();
      localStorage.setItem("admin_token", data.token);
      router.push("/dashboard");
    } catch (err: unknown) {
      setError((err as Error).message);
    }
  };

  const handleGoogleLogin = () => {
    alert("Login com Google");
  };

  const handleFacebookLogin = () => {
    alert("Login com Facebook");
  };

  const handleForgotPassword = () => {
    alert("Redefinir senha");
  };

  const handleSignUp = () => {
    alert("Criar conta");
  };

  return (
    <div className="min-h-screen flex items-center justify-center p-5">
      <div className="relative rounded-3xl p-12 w-full max-w-md shadow-2xl">
        <div className="text-center mb-10">
          <h2 className="text-black text-lg font-medium mb-2">Welcome to</h2>
          <h1 className="text-blue-900 text-7xl font-black tracking-wider my-4">
            FHAI
          </h1>
          <p className="text-black text-base font-medium leading-relaxed">
            Your smart financial
            <br />
            health assistant
          </p>
        </div>
        <form action="">
          <div className="space-y-5">
            {error && <div className="text-red-500 mb-4">{error}</div>}
            <div>
              <input
                type="email"
                placeholder="Email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                className="w-full px-5 py-4 rounded-xl bg-white bg-opacity-80 text-gray-800 text-base focus:outline-none focus:bg-white focus:shadow-lg transition-all duration-300"
                required
              />
            </div>

            <div>
              <input
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                className="w-full px-5 py-4 rounded-xl bg-white bg-opacity-80 text-gray-800 text-base focus:outline-none focus:bg-white focus:shadow-lg transition-all duration-300"
                required
              />
            </div>

            <div className="text-right">
              <button
                type="button"
                onClick={handleForgotPassword}
                className="text-blue-900 text-sm font-medium hover:text-blue-700 transition-colors duration-300 cursor-pointer"
              >
                Forgot password?
              </button>
            </div>

            <CardeDeTransacao
              key={1231}
              icon="NT"
              title="Netflix"
              subtitulo="Apr 05"
              tipo="-"
              value={29.9}
            />

            <CardeDeTransacao
              key={31312}
              icon="NT"
              title="Netflix"
              subtitulo="Apr 05"
              tipo="-"
              value={29.9}
            />

            <button
              type="submit"
              onClick={handleLogin}
              className="cursor-pointer w-full py-4 bg-teal-500 text-white rounded-full text-lg font-semibold mt-8 mb-6 hover:bg-teal-600 hover:shadow-xl hover:-translate-y-0.5 active:translate-y-0 transition-all duration-300 shadow-lg shadow-teal-500/30"
            >
              Sing in
            </button>
          </div>
        </form>

        <div className="flex justify-center gap-5 mb-8">
          <button
            onClick={handleGoogleLogin}
            className="w-12 h-12 rounded-full bg-white flex items-center justify-center hover:scale-110 hover:shadow-lg transition-all duration-300 shadow-md cursor-pointer"
            aria-label="Login com Google"
          >
            <svg viewBox="0 0 24 24" width="24" height="24">
              <path
                fill="#4285F4"
                d="M22.56 12.25c0-.78-.07-1.53-.2-2.25H12v4.26h5.92c-.26 1.37-1.04 2.53-2.21 3.31v2.77h3.57c2.08-1.92 3.28-4.74 3.28-8.09z"
              />
              <path
                fill="#34A853"
                d="M12 23c2.97 0 5.46-.98 7.28-2.66l-3.57-2.77c-.98.66-2.23 1.06-3.71 1.06-2.86 0-5.29-1.93-6.16-4.53H2.18v2.84C3.99 20.53 7.7 23 12 23z"
              />
              <path
                fill="#FBBC05"
                d="M5.84 14.09c-.22-.66-.35-1.36-.35-2.09s.13-1.43.35-2.09V7.07H2.18C1.43 8.55 1 10.22 1 12s.43 3.45 1.18 4.93l2.85-2.22.81-.62z"
              />
              <path
                fill="#EA4335"
                d="M12 5.38c1.62 0 3.06.56 4.21 1.64l3.15-3.15C17.45 2.09 14.97 1 12 1 7.7 1 3.99 3.47 2.18 7.07l3.66 2.84c.87-2.6 3.3-4.53 6.16-4.53z"
              />
            </svg>
          </button>

          <button
            onClick={handleFacebookLogin}
            className="w-12 h-12 rounded-full bg-white flex items-center justify-center hover:scale-110 hover:shadow-lg transition-all duration-300 shadow-md cursor-pointer"
            aria-label="Login com Facebook"
          >
            <svg viewBox="0 0 24 24" width="24" height="24">
              <path
                fill="#1877F2"
                d="M24 12.073c0-6.627-5.373-12-12-12s-12 5.373-12 12c0 5.99 4.388 10.954 10.125 11.854v-8.385H7.078v-3.47h3.047V9.43c0-3.007 1.792-4.669 4.533-4.669 1.312 0 2.686.235 2.686.235v2.953H15.83c-1.491 0-1.956.925-1.956 1.874v2.25h3.328l-.532 3.47h-2.796v8.385C19.612 23.027 24 18.062 24 12.073z"
              />
            </svg>
          </button>
        </div>

        <div className="text-center text-blue-900 text-sm">
          <p>Don&apos;t have an account?</p>
          <button
            onClick={handleSignUp}
            className="font-semibold border-b-2 border-blue-900 hover:text-blue-700 hover:border-blue-700 transition-colors duration-300 "
          >
            Sing up!
          </button>
        </div>
      </div>
    </div>
  );
}
