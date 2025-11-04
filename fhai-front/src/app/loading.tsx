export default function Loading() {
  return (
    <div className="mt-6 text-center">
      <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-teal-500 mx-auto"></div>
      <p className="mt-4 text-gray-600">Carregando ...</p>
    </div>
  );
}
