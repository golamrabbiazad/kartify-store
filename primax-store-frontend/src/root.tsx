// @refresh reload
import { Suspense } from "solid-js";
import {
  Body,
  ErrorBoundary,
  FileRoutes,
  Head,
  Html,
  Link,
  Meta,
  Routes,
  Scripts,
  Title,
} from "solid-start";
import { Headers } from "~/components/Headers";
import { Footer } from "~/components/Footer";
import "./root.css";

export default function Root() {
  return (
    <Html lang="en" class="h-full">
      <Head>
        <Title>Primax - A minimal ecommerce Store</Title>
        <Meta charset="utf-8" />
        <Meta name="viewport" content="width=device-width, initial-scale=1" />
        <Link rel="stylesheet" href="https://rsms.me/inter/inter.css" />
      </Head>
      <Body class="h-full">
        <Suspense>
          <ErrorBoundary>
            <Headers />
            <Routes>
              <FileRoutes />
            </Routes>
            <Footer />
          </ErrorBoundary>
        </Suspense>
        <Scripts />
      </Body>
    </Html>
  );
}
