import logo from '../assets/logo.png';
import styles from '../styles/Header.module.css';

export function Header(props) {
  return (
    <header className={styles.header}>
      <nav className={styles.nav}>
        <img src={logo} alt="Logo" className={styles.logo} />
        <ul className={styles.navList}>
          {props.links.map(link => (
            <li key={link.name} className={styles.navItem}>
              <a href={link.url} className={styles.navLink}>
                {link.name}
              </a>
            </li>
          ))}
        </ul>
      </nav>
    </header>
  );
}