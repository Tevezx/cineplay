import { useLoading } from '../context/LoadingContext';
import styles from '../styles/loading.module.css';

export function Loading() {
    const { isLoading } = useLoading();

    if (!isLoading) return null;

    return (
        <div className={styles.loadingOverlay}>
            <div className={styles.loadingContainer}>
                <div className={styles.spinner}></div>
                <p className={styles.text}>Carregando...</p>
            </div>
        </div>
    );
}
