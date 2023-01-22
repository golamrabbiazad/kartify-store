import { A } from "solid-start";

export default function NotFound() {
  return (
    <main class="min-h-screen flex flex-col items-center justify-center text-center mx-auto text-gray-700 p-4">
      <div class="max-6-xs my-16 tracking-tighter">
        <h1 class="text-4xl text-black font-bold ">Page not found.</h1>
        <p class="text-md text-gray-400">
          Sorry, we couldn't find the page you're looking for.
        </p>
      </div>
      <A href="/" class="text-indigo-700 hover:underline">
        Go back home -&gt;
      </A>
    </main>
  );
}
