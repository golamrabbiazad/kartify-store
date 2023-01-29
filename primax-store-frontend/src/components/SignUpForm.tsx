import { Show } from "solid-js";
import { FormError } from "solid-start";
import { createServerAction$, redirect } from "solid-start/server";
import { register } from "~/db/session";

function validateFirstName(firstName: unknown) {
  if (typeof firstName !== "string" || firstName.length < 4) {
    return `FirstName must be at least 4 characters long`;
  }
}

function validateLastName(lastName: unknown) {
  if (typeof lastName !== "string" || lastName.length < 3) {
    return `LastName must be at least 3 characters long`;
  }
}

function validateEmail(email: unknown) {
  if (typeof email !== "string" || email.length < 4) {
    return `Email must be at least 4 characters long`;
  }
}

function validatePassword(password: unknown) {
  if (typeof password !== "string" || password.length < 5) {
    return `Password must be at least 8 characters long`;
  }
}

function validatePhoneNumber(phoneNumber: unknown) {
  if (typeof phoneNumber !== "string" || phoneNumber.length < 9) {
    return `PhoneNumber must be at least 9 characters long`;
  }
}

function validateAddress(address: unknown) {
  if (typeof address !== "string") {
    return "Address not valid.";
  }
}

export function SignUpForm() {
  const [loggingIn, { Form }] = createServerAction$(async (form: FormData) => {
    const firstName = form.get("firstname");
    const lastName = form.get("lastname");
    const email = form.get("email");
    const password = form.get("password");
    const phoneNumber = form.get("phone");
    const address = form.get("address");

    if (
      typeof firstName !== "string" ||
      typeof lastName !== "string" ||
      typeof email !== "string" ||
      typeof password !== "string" ||
      typeof phoneNumber !== "string" ||
      typeof address !== "string"
    ) {
      throw new FormError(`Form not submitted correctly.`);
    }

    const fields = {
      firstName,
      lastName,
      email,
      password,
      phoneNumber,
      address,
    };

    const fieldErrors = {
      firstName: validateFirstName(firstName),
      lastName: validateLastName(lastName),
      email: validateEmail(email),
      password: validatePassword(password),
      phoneNumber: validatePhoneNumber(phoneNumber),
      address: validateAddress(address),
    };

    if (Object.values(fieldErrors).some(Boolean)) {
      throw new FormError(`Fields invalid`, {
        fieldErrors,
        fields,
      });
    }

    const newCustomer = await register({
      firstName,
      lastName,
      email,
      password,
      phoneNumber,
      address,
    });

    if (!newCustomer) {
      throw new FormError(`Somewhat details are incorrect.`, {
        fields,
      });
    }

    return redirect("/signin");
  });

  return (
    <div class="mt-10 sm:mt-0">
      <div class="md:grid md:grid-cols-3 md:gap-6">
        <div class="mt-5 md:col-span-3 md:mt-0">
          <Form>
            <div class="overflow-hidden shadow sm:rounded-md">
              <div class="bg-white px-4 py-5 sm:p-6">
                <div class="grid grid-cols-6 gap-6">
                  <div class="col-span-6 sm:col-span-3">
                    <label
                      for="firstname"
                      class="block text-sm font-medium text-gray-700"
                    >
                      First name
                    </label>
                    <input
                      type="text"
                      name="firstname"
                      id="firstname"
                      autocomplete="given-name"
                      class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    />
                  </div>
                  <Show when={loggingIn.error?.fieldErrors?.firstName}>
                    <p role="alert">{loggingIn.error.fieldErrors.firstName}</p>
                  </Show>

                  <div class="col-span-6 sm:col-span-3">
                    <label
                      for="lastname"
                      class="block text-sm font-medium text-gray-700"
                    >
                      Last name
                    </label>
                    <input
                      type="text"
                      name="lastname"
                      id="lastname"
                      autocomplete="family-name"
                      class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    />
                  </div>
                  <Show when={loggingIn.error?.fieldErrors?.lastName}>
                    <p role="alert">{loggingIn.error.fieldErrors.lastName}</p>
                  </Show>

                  <div class="col-span-6 sm:col-span-6">
                    <label
                      for="email"
                      class="block text-sm font-medium text-gray-700"
                    >
                      Email address
                    </label>
                    <input
                      type="text"
                      name="email"
                      id="email"
                      autocomplete="email"
                      required
                      class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    />
                  </div>
                  <Show when={loggingIn.error?.fieldErrors?.email}>
                    <p role="alert">{loggingIn.error.fieldErrors.email}</p>
                  </Show>

                  <div class="col-span-6 sm:col-span-6">
                    <label
                      for="password"
                      class="block text-sm font-medium text-gray-700"
                    >
                      Password
                    </label>
                    <input
                      id="password"
                      name="password"
                      type="password"
                      autocomplete="current-password"
                      class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    />
                  </div>
                  <Show when={loggingIn.error?.fieldErrors?.password}>
                    <p role="alert">{loggingIn.error.fieldErrors.password}</p>
                  </Show>

                  <div class="col-span-6 sm:col-span-6">
                    <label
                      for="phone"
                      class="block text-sm font-medium text-gray-700"
                    >
                      Phone Number
                    </label>
                    <input
                      type="text"
                      name="phone"
                      id="phone"
                      autocomplete="phone"
                      class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    />
                  </div>
                  <Show when={loggingIn.error?.fieldErrors?.phoneNumber}>
                    <p role="alert">
                      {loggingIn.error.fieldErrors.phoneNumber}
                    </p>
                  </Show>

                  <div class="col-span-6">
                    <label
                      for="street-address"
                      class="block text-sm font-medium text-gray-700"
                    >
                      Address
                    </label>
                    <input
                      type="text"
                      name="address"
                      id="address"
                      autocomplete="address"
                      class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    />
                  </div>
                  <Show when={loggingIn.error?.fieldErrors?.address}>
                    <p role="alert">{loggingIn.error.fieldErrors.address}</p>
                  </Show>
                </div>
              </div>
              <Show when={loggingIn.error}>
                <p role="alert" id="error-message">
                  {loggingIn.error.message}
                </p>
              </Show>
              <div class="bg-gray-50 px-4 py-3 text-right sm:px-6">
                <button
                  type="submit"
                  class="inline-flex justify-center rounded-md border border-transparent bg-indigo-600 py-2 px-4 text-sm font-medium text-white shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
                >
                  Sign Up
                </button>
              </div>
            </div>
          </Form>
        </div>
      </div>
    </div>
  );
}
