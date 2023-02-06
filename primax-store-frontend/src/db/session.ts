import { redirect } from "solid-start/server";
import { createCookieSessionStorage } from "solid-start/session";

type LoginForm = {
  email: string;
  password: string;
};

type RegisterForm = {
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  phoneNumber: string;
  address: string;
};

export async function register({
  firstName,
  lastName,
  email,
  password,
  phoneNumber,
  address,
}: RegisterForm) {
  try {
    const response = await fetch(
      `${import.meta.env.VITE_API_URL}/customers/register`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          firstName,
          lastName,
          email,
          password,
          phoneNumber,
          address,
        }),
      }
    );
    const user = await response.text();

    return user;
  } catch (error) {
    if (error instanceof Error) {
      console.error(error.message);
    }
  }
}

export async function login({
  email,
  password,
}: LoginForm): Promise<Customer | undefined> {
  try {
    const response = await fetch(
      `${import.meta.env.VITE_API_URL}/customers/login`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          email,
          password,
        }),
      }
    );

    const user = await response.json();
    if (!user) {
      throw new Error("User not found!");
    }

    const isCorrectPassword = password === user.password;
    if (!isCorrectPassword) {
      throw new Error("Password doesn't match.");
    }

    return user;
  } catch (error) {
    if (error instanceof Error) {
      console.error(error.message);
    }
  }
}

const sessionSecret = import.meta.env.VITE_SESSION_SECRET;

const storage = createCookieSessionStorage({
  cookie: {
    name: "RJ_session",
    // secure doesn't work on localhost for Safari
    // https://web.dev/when-to-use-local-https/
    secure: true,
    secrets: [sessionSecret],
    sameSite: "lax",
    path: "/",
    maxAge: 60 * 60 * 24 * 30, // 30 days
    httpOnly: true,
  },
});

export function getUserSession(request: Request) {
  console.log(request.headers.get("Cookie"));

  return storage.getSession(request.headers.get("Cookie"));
}

export async function getUserId(request: Request) {
  const session = await getUserSession(request);

  const userId = session.get("userId");
  if (!userId || typeof userId !== "string") return null;

  return userId;
}

// export async function requireUserId(
//   request: Request,
//   redirectTo: string = new URL(request.url).pathname
// ) {
//   const session = await getUserSession(request);
//   const userId = session.get("userId");
//   if (!userId || typeof userId !== "string") {
//     const searchParams = new URLSearchParams([["redirectTo", redirectTo]]);
//     throw redirect(`/signin?${searchParams}`);
//   }
//   return userId;
// }

type Customer = {
  customerId: string;
  firstName: string;
  lastName: string;
  email: string;
  password: string; // this should be hidden from backend when DTO redesigned.
  phoneNumber: string;
  address: string;
};

export async function getUser(request: Request): Promise<Customer | null> {
  const cookie = request.headers.get("Cookie") ?? "";
  const session = await storage.getSession(cookie);
  const userId = session.get("userId");
  if (!userId) return null;

  try {
    const response = await fetch(
      `${import.meta.env.VITE_API_URL}/customers/${userId}`
    );
    const user = await response.json();

    return user;
  } catch {
    throw logout(request);
  }
}

export async function logout(request: Request) {
  const session = await storage.getSession(request.headers.get("Cookie"));
  return redirect("/signin", {
    headers: {
      "Set-Cookie": await storage.destroySession(session),
    },
  });
}

export async function createUserSession(userId: string, redirectTo: string) {
  const session = await storage.getSession();
  session.set("userId", userId);
  return redirect(redirectTo, {
    headers: {
      "Set-Cookie": await storage.commitSession(session),
    },
  });
}
