import { FC, useState } from 'react'
import styles from './Table.module.scss'
import ButtonAdd from '../buttons/button-add/ButtonAdd.tsx'
import { ITableItem } from '../../types/components/table/table-props-type.ts'
import TableItem from './table-item/TableItem.tsx'

const Table: FC = () => {
    const [items, setItems] = useState<ITableItem[]>([
        {
            id: '1',
            field1: 'Test1',
            field2: 'Test2'
        },
        {
            id: '2',
            field1: 'Test3',
            field2: 'Test4'
        }
    ])

    const handlerRemove = (id: string) => {
        setItems(items.filter(i => i.id !== id))
    }

    const handlerAdd = () => {
        const id = String(new Date().getMilliseconds())
        setItems(items.concat({ id: id, field1: `Test1-${id}`, field2: `Test2-${id}` }))
    }


    return (
        <div className={styles.table}>
            {items.map(item =>
                <TableItem
                    key={item.id}
                    id={item.id}
                    field1={item.field1}
                    field2={item.field2}
                    handlerRemove={handlerRemove}
                />
            )}
            <div className='mt-4 flex justify-center'>
                <ButtonAdd handlerAdd={handlerAdd}/>
            </div>
        </div>
    )
}

export default Table
