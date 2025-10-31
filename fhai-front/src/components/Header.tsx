import Link from "next/link";

export default function Header() {
  return (
    <header className="shadow-sm">
      <div className="container mx-auto flex items-center justify-between p-4">
        <Link href="/" className="text-xl font-bold">
          ICON_LOGO
        </Link>
      </div>
    </header>
  );
}
