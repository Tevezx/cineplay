import styles from "../../styles/ModalExclusao.module.css";

export function ModalExclusao({ onClose, onExcluido }) {
    return (
        <div className={styles.overlay}>
            <div className={styles.modal}>
                <h2 className={styles.title}>Confirmação de Exclusão</h2>
                <p className={styles.texto}>Tem certeza que deseja excluir este item?</p>
                <div className={styles.acoes}>
                    <button className={styles.confirmarBtn} onClick={onExcluido} type="button">Sim</button>
                    <button className={styles.cancelarBtn} onClick={onClose} type="button">Não</button>
                </div>
            </div>
        </div>
    );
}