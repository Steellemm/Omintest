import { FC } from 'react'
import styles from './Search.module.scss'
import svg from '../../assets/svg/sprite.svg'
import { SearachPropsType as SerachPropsType } from '../../types/components/searchs/searach-props-type.ts'
import cn from 'classnames'

const Search: FC<SerachPropsType> = ({ small }) => {
    return (
        <div className={styles.search}>
            <svg className={cn(styles.icon, small && styles.iconSmall)}>
                <use href={`${svg}#search`}/>
            </svg>
            <input
                type="text"
                className={cn(styles.input, small && styles.inputSmall)}
                placeholder="Поиск..."
            />
        </div>
    )
}

export default Search
