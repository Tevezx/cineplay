import { Link } from 'react-router-dom';
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
              <Link
                to={link.url}
                className={`${styles.navLink} ${link.isActive ? styles.navLinkActive : ''}`}
              >
                {link.name}
              </Link>
            </li>
          ))}
        </ul>
      </nav>
    </header>
  );
}