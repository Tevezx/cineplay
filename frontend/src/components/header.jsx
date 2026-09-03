import logo from '../assets/logo.png';

export function Header(props){
    return(
        <header>
            <nav>
                <ul>
                    <img src={logo} alt="Logo" />
                    {props.links.map(link => (
                        <li key={link.name}>
                            <a href={link.url}>{link.name}</a>
                        </li>
                    ))}
                </ul>
            </nav>
        </header>
    ); 
}