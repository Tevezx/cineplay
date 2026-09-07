import { useEffect, useState } from 'react';

let isLoading = false;
let listeners = [];

function notificar() {
    listeners.forEach((listener) => listener(isLoading));
}

export function startLoading() {
    isLoading = true;
    notificar();
}

export function stopLoading() {
    isLoading = false;
    notificar();
}

export function useLoading() {
    const [loading, setLoading] = useState(isLoading);

    useEffect(() => {
        listeners.push(setLoading);
        return () => {
            listeners = listeners.filter((listener) => listener !== setLoading);
        };
    }, []);

    return { isLoading: loading, startLoading, stopLoading };
}
