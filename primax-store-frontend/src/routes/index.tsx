import { createResource, For, Show } from "solid-js";
import { A, useRouteData } from "solid-start";
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
    <Show when={products} fallback={<p>Products could not fetched...</p>}>
      <div class="bg-white">
        <div class="mx-auto max-w-2xl py-16 px-4 sm:py-24 sm:px-6 lg:max-w-7xl lg:px-8">
          <div class="mt-6 grid grid-cols-1 gap-y-10 gap-x-6 sm:grid-cols-2 lg:grid-cols-4 xl:gap-x-8">
            <For each={products()} fallback={<div>Loading...</div>}>
              {(product) => (
                <div>
                  <A href={`/product/${product.productId}`}>
                    <div class="group relative">
                      <div class="min-h-80 aspect-w-1 aspect-h-1 w-full overflow-hidden rounded-md bg-gray-200 group-hover:opacity-75 lg:aspect-none lg:h-80">
                        <img
                          src={product.image}
                          alt={product.name}
                          class="h-full w-full object-cover object-center lg:h-full lg:w-full"
                        />
                      </div>
                      <div class="mt-4 flex justify-between">
                        <div>
                          <h3 class="text-sm text-gray-700">
                            <span
                              aria-hidden="true"
                              class="absolute inset-0"
                            ></span>
                            {product.name}
                          </h3>
                          <p class="mt-1 text-sm text-gray-500">
                            {product.description}
                          </p>
                        </div>
                        <p class="text-sm font-medium text-gray-900">
                          ${product.price}
                        </p>
                      </div>
                    </div>
                  </A>
                </div>
              )}
            </For>
          </div>
        </div>
      </div>
    </Show>
  );
}
