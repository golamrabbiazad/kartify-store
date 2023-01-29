import { createResource } from "solid-js";
import { useRouteData } from "solid-start";
import {
  createServerAction$,
  createServerData$,
  redirect,
} from "solid-start/server";
import { Products } from "~/components/Products";
import { getUser, logout } from "~/db/session";
import { ProductType } from "~/types/product";

export function routeData() {
  const [products] = createResource(async () => {
    try {
      const response = await fetch(`${import.meta.env.VITE_API_URL}/products`);
      return (await response.json()) as ProductType[];
    } catch (error) {
      console.log("could not connect api");
    }
  });

  return {
    products,
  };
}

export default function Home() {
  const { products } = useRouteData<typeof routeData>();

  return (
    <>
      <Products products={products} />
    </>
  );
}
