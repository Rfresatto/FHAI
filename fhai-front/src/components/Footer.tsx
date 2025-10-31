export default function Footer() {
  return (
    <footer className="mt-12">
      <div className="container mx-auto p-6 text-center text-sm text-gray-700">
        © {new Date().getFullYear()} FHAI - Finance Health Assistance
        Intelligence. Todos os direitos reservados.
      </div>
    </footer>
  );
}
