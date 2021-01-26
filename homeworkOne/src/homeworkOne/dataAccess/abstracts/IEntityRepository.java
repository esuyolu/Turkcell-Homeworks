package homeworkOne.dataAccess.abstracts;

import java.util.List;

import homeworkOne.entities.abstracts.IEntity;

public interface IEntityRepository<T extends IEntity> {
	void add(T item);
	void update(T item);
	void delete(T item);
	List<T> getAll();
}
