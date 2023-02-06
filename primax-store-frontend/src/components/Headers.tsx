import { Show } from "solid-js";
import { A, useRouteData } from "solid-start";
import {
  createServerAction$,
  createServerData$,
  redirect,
} from "solid-start/server";
import { getUser, logout } from "~/db/session";

export function routeData() {
  return createServerData$(async (_, { request }) => {
    // const user = await getUser(request);
    // if (!user) {
    //   throw redirect("/signin");
    // }

    return {
      customerId: "287e92a9-d023-4264-8342-2c616884fb78",
      firstName: "Brian",
      lastName: "Holt",
      email: "brianholt@stripe.com",
      password: "stripe",
      phoneNumber: "56985654521",
      address: "Seattle, USA",
    };
  });
}

export function Headers() {
  const user = useRouteData<typeof routeData>();
  const [, { Form }] = createServerAction$((form: FormData, { request }) =>
    logout(request)
  );

  return (
    <div class="relative bg-white">
      <div class="mx-auto max-w-7xl px-6">
        <div class="flex items-center justify-between border-b-2 border-gray-100 py-6 md:justify-start md:space-x-10">
          <div class="flex justify-start lg:w-0 lg:flex-1">
            <A href="/">
              <span class="sr-only">Primax</span>
              <img
                class="h-8 w-auto sm:h-10"
                src="https://tailwindui.com/img/logos/mark.svg?color=indigo&shade=600"
                alt=""
              />
            </A>
          </div>
          <div class="-my-2 -mr-2 md:hidden">
            <button
              type="button"
              class="inline-flex items-center justify-center rounded-md bg-white p-2 text-gray-400 hover:bg-gray-100 hover:text-gray-500 focus:outline-none focus:ring-2 focus:ring-inset focus:ring-indigo-500"
              aria-expanded="false"
            >
              <span class="sr-only">Open menu</span>

              <svg
                class="h-6 w-6"
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                stroke-width="1.5"
                stroke="currentColor"
                aria-hidden="true"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  d="M3.75 6.75h16.5M3.75 12h16.5m-16.5 5.25h16.5"
                />
              </svg>
            </button>
          </div>

          <div class="hidden items-center justify-end md:flex md:flex-1 lg:w-0">
            <div>{"USER"}</div>
            <Form>
              <Show
                when={true}
                fallback={
                  <button>
                    <A
                      href="/signin"
                      class="ml-8 inline-flex items-center justify-center whitespace-nowrap rounded-md border border-transparent bg-indigo-600 px-4 py-2 text-base font-medium text-white shadow-sm hover:bg-indigo-700"
                    >
                      Sign In
                    </A>
                  </button>
                }
              >
                <button type="submit" name="logout">
                  Logout
                </button>
              </Show>
            </Form>
          </div>
        </div>
      </div>

      <div class="inset-x-0 top-0 origin-top-right transform p-2 transition md:hidden">
        <div class="rounded-lg bg-white shadow-lg ring-black ring-opacity-5"></div>
        <div class="space-y-6 py-6 px-5">
          <A
            href="/signup"
            class="flex w-full items-center justify-center rounded-md border border-transparent bg-indigo-600 px-4 py-2 text-base font-medium text-white shadow-sm hover:bg-indigo-700"
          >
            Sign up
          </A>
          <p class="mt-6 text-center text-base font-medium text-gray-500">
            Existing customer? {""}
            <A href="/signin" class="text-indigo-600 hover:text-indigo-500">
              Sign in
            </A>
          </p>
        </div>
      </div>
    </div>
  );
}
