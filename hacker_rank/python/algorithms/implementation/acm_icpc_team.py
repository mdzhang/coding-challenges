import copy

people, topics = map(lambda x: int(x), raw_input().split(' '))

matrix = []
max_topics_known = 0
teams_that_know_max_topics = 0

for p in range(people):
    row = map(lambda x: int(x), list(raw_input()))
    matrix.append(row)

min_checked = 0

for i, person_a in enumerate(matrix):
    for j, person_b in enumerate(matrix[min_checked:]):

        if i != j:
            topics_known = copy.copy(person_a)

            for idx, known in enumerate(person_b):
                if known:
                    topics_known[idx] = known

            total_topics_known = sum(topics_known)

            if total_topics_known > max_topics_known:
                max_topics_known = total_topics_known
                teams_that_know_max_topics = 1
            elif total_topics_known == max_topics_known:
                teams_that_know_max_topics += 1

    min_checked += 1

print max_topics_known
print teams_that_know_max_topics


