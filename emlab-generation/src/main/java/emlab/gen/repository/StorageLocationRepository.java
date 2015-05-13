/*******************************************************************************
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package emlab.gen.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.annotation.QueryType;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import emlab.gen.domain.technology.PowerGeneratingTechnology;
import emlab.gen.domain.technology.PowerGridNode;
import emlab.gen.domain.technology.StorageLocation;

public interface StorageLocationRepository extends GraphRepository<StorageLocation> {
    @Query(value = "g.v(technology).in('STORAGE_TECHNOLOGY').filter{it.__type__=='emlab.gen.domain.technology.StorageLocation'}.as('s').in('STORAGE_NODE').filter{it==g.v(node)}.back('s').next()", type = QueryType.Gremlin)
    StorageLocation findStorageLocationByTechnologyAndNode(@Param("technology") PowerGeneratingTechnology technology,
            @Param("node") PowerGridNode node);

    @Query(value = "g.v(node).out('STORAGE_NODE').filter{it.__type__=='emlab.gen.domain.technology.StorageLocation'}.next()", type = QueryType.Gremlin)
    StorageLocation findStorageLocationByNode(@Param("node") PowerGridNode node);

}

