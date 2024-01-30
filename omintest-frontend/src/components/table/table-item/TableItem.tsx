import { FC } from 'react'
import styles from './TableItem.module.scss'
import Cell from '../cell/Cell.tsx'
import ButtonClose from '../../buttons/button-close/ButtonClose.tsx'
import { ITableItemPropsType } from '../../../types/components/table/table-props-type.ts'


const TableItem: FC<ITableItemPropsType> = (props) => {
    return (
        <div className={styles.item}>
            <div className={styles.inner}>
                <Cell text={props.field1}/>
                <Cell text={props.field2}/>
            </div>
            <ButtonClose handlerRemove={props.handlerRemove} id={props.id}/>
        </div>
    )
}

export default TableItem
